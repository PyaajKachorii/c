from http.server import HTTPServer, BaseHTTPRequestHandler

class SimpleCloudApp(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()
        self.wfile.write(b"<h1>Cloud Computing Practical: Docker Service Running Successfully!</h1>")

if __name__ == "__main__":
    server = HTTPServer(('0.0.0.0', 8080), SimpleCloudApp)
    print("[*] Containerized service listening on port 8080...")
    server.serve_forever()

























    







'''cd C:\cloud-demo-app
docker build -t cloud-demo-app .

docker run -d -p 8080:8080 --name cloud_container cloud-demo-app


docker stop cloud_container

docker rm cloud_container'''
