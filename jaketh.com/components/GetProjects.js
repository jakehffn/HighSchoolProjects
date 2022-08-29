class Project {
    constructor(title, tags, description, image, page) {
    this.title = title;
    this.tags = tags;
    this.description = description;
    this.image = image;
    this.page = page;
    }
}

function addToProjects(title, tags, description, image, projects, page) {
    var project = new Project(title, tags, description, image, page);
    projects.push(project);
}

const getAllProjects = () => {

    var projects = [];

    addToProjects("Personal Website", ["projects", "web-development"], 
    "The personal website of Jacob Hoffman",
    "https://storage.googleapis.com/jaketh/resources/BigfootSmaller.jpg", projects,
    <div>hello</div>
    );

    addToProjects("Game of Life", ["projects", "software-development"], 
    "A recreation of Conways \"Game of Life\" made in Java using Swing",
    "https://storage.googleapis.com/jaketh/resources/BetterGameOfLife.png", projects,
    <div>hello</div>
    );

    addToProjects("Collision Simulator", ["projects", "software-development"], 
    "Calculating PI using collisions; Based on research paper",
    "https://storage.googleapis.com/jaketh/resources/CollisionSimulator.png", projects,
    <div>hello</div>
    );

    addToProjects("Fluid Simulator", ["projects", "software-development"], 
    "Basic fluid simulator based on research paper",
    "https://storage.googleapis.com/jaketh/resources/FluidSimulator.png", projects,
    <div>hello</div>
    );

    return projects;
}

export default getAllProjects;