# Etapa de copilacion
FROM node:22 AS build
WORKDIR /app
package*.json ./
RUN npm install
COPY . .

RUN npm run build

# Etapa de despliegue
FROM nginx
COPY --from=build /app/dist/erp-frontend/browser /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
