
import { useState } from "react";


function Ex4ToDoList2() {

    const [datas, setDatas] = useState({todo:''});
    const [num, setNum] = useState(0);
    const [todos, setTodos] =useState([]);

    const submit = (e) => {
        e.preventDefault();

        if(!datas.todo || datas.todo.length === 0){
            alert("내용을 입력하세요.");
            return;
        }

        setTodos([...todos,{
            num : num+1,
            todo : datas.todo
        }])
        setNum(num+1)
        
        setDatas({...datas, todo: ''})
        
        
    }
    const inputChange = (e) => {
        const{name, value} = e.target;
        setDatas({...datas,[name]: value})
    }

    const deleteTodo = num => {
        const deletedTodos = [...todos].filter(todo=>{
            return todo.num !== num;
        });

        setTodos(deletedTodos);

    }


    
    return(
        <div>
            <form onSubmit={submit}>
            <input type="text" name="todo" onChange={inputChange} value={datas.todo}/>
            <button>등록</button>
            </form>
            <h1>오늘의 할일</h1>
            <ul>
            {
               todos.map((value, index)=>{
                  return (
                     <li key={value.num}>{value.todo}
                     <button type='button' onClick={()=>deleteTodo(value.num)}>&times;</button>
                     </li>
                  )
               })
            }
         </ul>
        </div>
    )
    
}

export default Ex4ToDoList2;