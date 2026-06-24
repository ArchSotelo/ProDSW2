# Etapa de compilación
FROM node:22 AS build
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
ENV PATH="./node_modules/.bin:$PATH"

RUN npm run build --configuration production

# Etapa de despliegue
FROM nginx:alpine
COPY --from=build /app/dist/erp-frontend/browser /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
