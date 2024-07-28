import { authenticate_api_url } from "@/constants/api";
import { AuthenticationResponse, User } from "@/models/type";
import { NextResponse } from "next/server";
const url = `${authenticate_api_url}/login`;

export async function POST(req: Request) {
  const request = await req.json();
  const loginResponse = await fetch(url, {
    body: JSON.stringify(request),
    method: "POST",
    headers: new Headers({ "content-type": "application/json" }),
  });

  if (loginResponse.status > 300) {
    return new Response(await loginResponse.text(), {
      status: loginResponse.status,
    });
  }
  const authenticationResponse = (await loginResponse.json()) as AuthenticationResponse;
  const jwt = authenticationResponse.jwt;
  const response = NextResponse.json(JSON.stringify(authenticationResponse.user), {
    status: 200,
  });
  const jwtValue = `Bearer ${jwt}`;
  response.cookies.set({
    name: "Authorization",
    value: jwtValue,
    httpOnly: true,
  });
  return response;
}
