# Stage 1: Build the Angular application
FROM node:20-alpine AS build

# Set the working directory
WORKDIR /app

# Install Angular CLI globally (if needed)
RUN npm install -g @angular/cli

# Copy package.json and package-lock.json separately for efficient caching
COPY package.json package-lock.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the Angular application with production configuration
RUN npm run build