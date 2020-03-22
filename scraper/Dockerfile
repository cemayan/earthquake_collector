FROM golang:alpine

WORKDIR /app
COPY go.mod ./
RUN go mod download
COPY . .
RUN go build -o main .
RUN apk add --no-cache bash
EXPOSE 8085
CMD ["./main"]


