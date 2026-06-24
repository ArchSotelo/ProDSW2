# Etapa de compilación
FROM node:22 AS build
WORKDIR /app

COPY package*.json ./
RUN npm install   # instala también devDependencies

COPY . .
RUN npx ng build --configuration production

# Etapa de despliegue
FROM nginx
COPY --from=build /app/dist/erp-frontend/browser /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
