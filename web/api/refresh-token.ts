import { config } from "process";
import axios from "axios";

export async function refreshAccessToken() {
  const url = "/api/refresh-access-token";
  var axiosResponse = await axios.post(url, config);
  return axiosResponse.data;
}
