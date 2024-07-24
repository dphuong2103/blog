export function formatDate(input: string | number): string {
  let date: Date;

  if (typeof input === "number") {
    date = new Date(input * 1000);
  } else if (typeof input === "string") {
    input = input.split(" ").join("T") + "Z";
    date = new Date(input);
  } else {
    throw new Error("Invalid input type");
  }

  return date.toLocaleDateString("en-EN", {
    month: "long",
    day: "numeric",
    year: "numeric",
    timeZone: "Asia/Ho_Chi_Minh",
  }) + ' ' + date.toLocaleTimeString("en-EN", {
    hour: '2-digit',
    minute: '2-digit',
    hour12: false,
    timeZone: "Asia/Ho_Chi_Minh",
  });
}
