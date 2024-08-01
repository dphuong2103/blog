import axios, { AxiosRequestConfig } from "axios";
import { refreshAccessToken } from "./refresh-token";
import { redirect } from "next/navigation";
export const config: AxiosRequestConfig = {
  withCredentials: true,
  headers: {
    "content-type": "application/json",
  },
};

const axiosInstanceWithCredential = axios.create({
  ...config,
});

axiosInstanceWithCredential.interceptors.response.use(
  (response) => {
    console.log("Response: ", response);
    return response;
  },
  async (error) => {
    const originalRequest = error.config;
    if (
      error.response.status === 401 &&
      error.response.statusText === "Unauthorized" &&
      (originalRequest.url === "/api/refresh-access-token" ||
        originalRequest.url === "api/refresh-access-token")
    ) {
      redirect("/login");
    }
    if (
      error.response.status === 401 &&
      error.response.statusText === "Unauthorized"
    ) {
      try {
        await refreshAccessToken();
        return axiosInstanceWithCredential(originalRequest);
      } catch (e) {
        console.error("Error refresh token: ", e);
        redirect("/login");
      }
    }

    return Promise.reject(error);
  },
);

export { axiosInstanceWithCredential };
