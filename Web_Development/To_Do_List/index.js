var todoList = [];
const countBtn = document.getElementById("count-btn");
const myList = document.getElementById("my-list");
const todoInput = document.getElementById("todoInput");



function renderTodoList() {
    myList.innerHTML = "";
    let i = 0;
    todoList.forEach((todo) => {
        const li = document.createElement("li");
        li.innerHTML = `
            <div class="todoelements">
            <h3>${todo.title}</h3>
            <button class="completed" onClick="deleteTodo(event,${i})">COMPLETED</button>
            </div>
        `
        myList.appendChild(li);
        i++;
    })
}

function addtoList(event) {
    event.preventDefault();
    const newTodo = todoInput.value;
    if (newTodo != "") {
        todoList.push({ title: newTodo })
        todoInput.value = "";
        localStorage.setItem("todos", JSON.stringify(todoList));
        renderTodoList();
    }
}


function deleteTodo(event, index) {
    event.preventDefault();
    todoList.splice(index, 1);
    localStorage.setItem("todos", JSON.stringify(todoList));
    renderTodoList();
}

document.addEventListener("DOMContentLoaded", (event) => {
    var todos = localStorage.getItem("todos");
    if (todos != null) {
        todoList = JSON.parse(todos);
        renderTodoList();
    }

})
