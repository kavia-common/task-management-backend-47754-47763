# task-management-backend-47754-47763

Backend Spring Boot service exposing Task CRUD API with in-memory storage.

Key endpoints:
- GET /tasks
- GET /tasks/{id}
- POST /tasks
- PUT /tasks/{id}
- DELETE /tasks/{id}
- Health: GET /health
- Swagger UI: /swagger-ui.html (or GET /docs redirects appropriately)

On startup, a few sample tasks are seeded for testing.