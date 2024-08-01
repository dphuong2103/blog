import { FailedResult } from "@/models/type";

export function isFailedResult(error: unknown): error is FailedResult {
  if (typeof error === "object" && error) {
    return "success" in error && "message" in error;
  }
  return false;
}
