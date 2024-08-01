import { user_token_api_url } from "@/constants/api";
import { AuthenticationResponse } from "@/models/type";
import { NextResponse } from "next/server";
import cookie from "cookie";
const url = `${user_token_api_url}/refresh-access-token`;

export async function GET(req: Request) {
  return NextResponse.json({ alive: true });
}

export async function POST(req: Request) {
  const requestHeaders = new Headers(req.headers);
  const cookies = cookie.parse(req.headers.get("cookie") || "");
  if (cookies.refreshToken) {
    requestHeaders.set("refreshToken", cookies.refreshToken);
  }
  const refreshTokenResponse = await fetch(url, {
    method: "POST",
    headers: requestHeaders,
  });
  if (refreshTokenResponse.status > 300) {
    return new Response(await refreshTokenResponse.json(), {
      status: refreshTokenResponse.status,
    });
  }
  const authenticationResponse =
    (await refreshTokenResponse.json()) as AuthenticationResponse;

  const jwt = authenticationResponse.jwt;
  const response = NextResponse.json(
    JSON.stringify(authenticationResponse.user),
    {
      status: 200,
    },
  );
  const jwtValue = `Bearer ${jwt}`;
  response.cookies.set({
    name: "Authorization",
    value: jwtValue,
    httpOnly: true,
  });
  response.cookies.set({
    name: "refreshToken",
    value: authenticationResponse.refreshToken,
    httpOnly: true,
  });
  return response;
}
