import express from "express";
import bodyParser from "body-parser";
import cors from "cors";
import apiRoutes from "./routes";
import dotenv from "dotenv";

dotenv.config();
const app = express();
app.use(cors());
app.use(bodyParser.json());
app.use("/api/v1", apiRoutes);

export default app;
