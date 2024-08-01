import { user_api_url } from "@/constants/api";
import { User } from "@/models/type";
import { cookies } from "next/headers";

export function getJwt() {
  const cookieStore = cookies();
  const session = cookieStore.get("Authorization")?.value;
  return session?.split(" ")[1];
}

export async function logout() {
  const jwt = getJwt();
  if (!jwt) return;
  cookies().set("Authorization", "", { expires: new Date(0) });
}

export async function getUserFromJwt(authorizationString: string) {
  const url = `${user_api_url}/info`;
  const response = await fetch(url, {
    headers: {
      Authorization: authorizationString,
    },
  });
  if (!response.ok) {
    return null;
  }
  return (await response.json()) as User;
}
