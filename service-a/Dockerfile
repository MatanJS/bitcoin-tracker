FROM node:18-alpine

WORKDIR /service-a

COPY package*.json ./
RUN npm install

COPY . .
EXPOSE 80
RUN npm run build

CMD ["node", "dist/server.js"]