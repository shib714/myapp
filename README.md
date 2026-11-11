This is a multi module maven shell project. 

# myapp - root of the project

myapp is a full-stack enterprise application built with a Maven multi-module architecture. It combines Java EE backend services, a REST API, and an Angular frontend into a deployable WebLogic EAR package.

## Architecture Overview

The project is organized as a parent Maven build with four modules:

```text
myapp/
├── myapp-cfg/        # Any configuration file like log4j2.xml
├── myapp-ear/        # EAR packaging module for deployment
├── myapp-ejb/        # EJB module for business logic and persistence
├── myapp-rest/       # JAX-RS REST layer
├── myapp-ng/         # Angular web application packaged as a WAR
│   └── ng/           # Angular workspace and source files
└── pom.xml           # Parent Maven build definition
```

### Modules and responsibilities
- myapp-cfg
  - Contains any configuration file like log4j2.xml.

- myapp-ear
  - Packages the EJB, REST, and Angular web modules into a single EAR artifact for WebLogic.

- myapp-ejb
  - Contains EJB services and persistence-oriented business logic.
  - Uses Java EE APIs such as EJB and JPA.

- myapp-rest
  - Exposes REST endpoints through JAX-RS.
  - Depends on the EJB module and serves API traffic for the frontend.

- myapp-ng
  - Hosts the Angular frontend application.
  - Uses Angular 22 with standalone components, routing, SCSS, and TypeScript.
  - The Angular build is packaged into the WAR for deployment.



## Technologies Used

### Backend
- Java 21
- Maven 3.x
- Java EE 8 APIs via javax.* packages
- EJB 3.2
- JPA 2.2
- JAX-RS 2.1.1
- Jersey 2.45
- WebLogic-compatible EAR packaging

### Frontend
- Angular 22
- Angular CLI
- TypeScript 6.x
- RxJS
- SCSS
- Vitest for testing

### Build and Dev Tools
- Maven frontend plugin for Node/npm provisioning
- Node.js 24.x and npm 11.x during Maven builds
- Angular development server with a proxy configuration for local API calls

## Prerequisites

Before building or running the project, make sure you have:

- Java 21 or later
- Maven 3.9+
- Node.js and npm (optional for local Angular development; Maven can provision them automatically)
- A WebLogic server instance (for deployment)

## Quick Start

### 1) Clone the repository

```bash
git clone <repository-url>
cd myapp
```

### 2) Build the full application

```bash
mvn clean package
```

This will:
- install Node/npm through the Maven frontend plugin,
- install Angular dependencies,
- build the Angular app,
- package the frontend into a WAR,
- assemble the final EAR artifact,
- run the Maven Antrun step that copies the EAR into the WebLogic autodeploy directory configured in the parent POM.

The packaged EAR will be created at:

```text
myapp-ear/target/myapp-ear-1.0.0-SNAPSHOT.ear
```

### 3) Run the Angular frontend locally

```bash
cd myapp-ng/ng
npm install
npm start
```

Then open:

```text
http://localhost:4200
```

The Angular app is configured to proxy API requests to the backend via the settings in [myapp-ng/ng/proxy.conf.json](myapp-ng/ng/proxy.conf.json).

### 4) Deploy to WebLogic (Manual, but a plugin can be used)

Deploy the EAR artifact copying from the target folder to a WebLogic domain. After deployment, the application is typically available at:

- Frontend: `/myapp/ng`
- REST API: `/myapp/rest/api`

## Local Development Notes

- The Angular app is intended to be served locally with the dev server while the backend is available through the deployed WebLogic environment.
- The frontend proxy configuration forwards `/rest` requests to the WebLogic context root at `http://localhost:7001/myapp`.

## API Endpoints

The REST API is exposed from the myapp-rest module and is typically served under the WebLogic context root:

```text
/myapp/rest/api
```

### Current endpoint patterns

- `GET /myapp/rest/api/products`
  - Returns the list of products.
- `GET /myapp/rest/api/products/{id}`
  - Returns a single product by ID.
- `POST /myapp/rest/api/products`
  - Creates a new product.
- `PUT /myapp/rest/api/products/{id}`
  - Updates an existing product.
- `DELETE /myapp/rest/api/products/{id}`
  - Deletes a product by ID.

> The exact request and response payloads depend on the current implementation in the REST layer.

## Testing

### Backend tests

```bash
mvn test
```

### Frontend tests

```bash
cd myapp-ng/ng
npm test
```

## Build Output

The Angular production build is emitted to:

```text
myapp-ng/ng/dist/myapp-ng/browser/
```

That output is packaged into the WAR artifact for deployment.

## Git Ignore Notes

Generated artifacts, build output, dependencies, and IDE files are ignored to keep the repository clean. Common ignored paths include:

- `myapp-ng/ng/node_modules/`
- `myapp-ng/ng/dist/`
- `myapp-ng/ng/.angular/`
- `**/target/`
- `.classpath`, `.project`, `.settings/`, `.idea/`, `.vscode/`

## Contributing

Pull requests are welcome. For significant changes, please open an issue first to discuss the proposed update.