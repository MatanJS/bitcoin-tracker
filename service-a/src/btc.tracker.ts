import axios, { AxiosRequestConfig } from "axios";

const prices: number[] = [];

export async function getBitcoinPrice(): Promise<void> {
  const headers: AxiosRequestConfig = {
    headers: {
      "X-Api-key": process.env.API_KEY,
    },
  };
  setInterval(async () => {
    try {
      const response = await axios.get(
        "https://api.api-ninjas.com/v1/bitcoin",
        headers
      );
      const price = response.data.price;
      const now = new Date().toLocaleString();
      console.log(`[${now}] Bitcoin Price: $${price}`);
      prices.push(parseFloat(price));
      if (prices.length > 10) prices.shift();
      if (prices.length === 10) {
        const avg = prices.reduce((a, b) => a + b, 0) / prices.length;
        console.log(`[${now}] 10-min Average: $${avg.toFixed(2)}`);
      }
    } catch (error) {
      console.error("Error getting Bitcoin price:", error);
    }
  }, 60000);
}
