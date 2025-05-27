import { Router } from "express";
import { getBitcoinPrice } from "./btc.tracker";

const router = Router();

router.get("/health", (_, res) => {
  res.send("I am Healthy");
});
router.get("/get-btc", (_, res) => {
  getBitcoinPrice();
  res.send("fetching bitcoin values - check your server logs");
});

export default router;
