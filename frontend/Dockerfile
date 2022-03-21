FROM node:alpine

WORKDIR /app

COPY package*.json ./
COPY tsconfig.json ./
COPY yarn.lock ./
COPY ./ ./

RUN yarn install

EXPOSE 3000

CMD [ "yarn", "start" ]