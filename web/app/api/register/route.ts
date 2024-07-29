import { authenticate_api_url } from "@/constants/api";
import { AuthenticationResponse } from "@/models/type";
import { NextResponse } from "next/server";

export async function GET() {
  return new Response("hello", {
    status: 200,
  });
}

export async function POST(req: Request) {
  const request = await req.json();
  const url = `${authenticate_api_url}/register`;
  const registerResponse = await fetch(url, {
    body: JSON.stringify(request),
    method: "POST",
    headers: new Headers({ "content-type": "application/json" }),
  });

  if (registerResponse.status > 300) {
    return new Response(await registerResponse.text(), {
      status: registerResponse.status,
    });
  }
  const authenticationResponse =
    (await registerResponse.json()) as AuthenticationResponse;
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
