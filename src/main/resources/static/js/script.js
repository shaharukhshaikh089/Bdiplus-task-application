// JavaScript for frontend interaction
document.addEventListener("DOMContentLoaded", () => {
    const taskList = document.querySelector("tbody");
    const addTaskForm = document.getElementById("addTaskForm");

    // Fetch and display tasks when the page loads
    fetch("/api/tasks")
        .then((response) => response.json())
        .then((tasks) => {
            tasks.forEach((task) => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${task.title}</td>
                    <td>${task.description}</td>
                `;
                taskList.appendChild(row);
            });
        });

    // Handle form submission to add a new task
    addTaskForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const title = document.getElementById("title").value;
        const description = document.getElementById("description").value;
        const newTask = {
            title: title,
            description: description,
        };

        fetch("/api/tasks", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newTask),
        })
            .then((response) => response.json())
            .then((task) => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${task.title}</td>
                    <td>${task.description}</td>
                `;
                taskList.appendChild(row);
                addTaskForm.reset();
            });
    });
});
