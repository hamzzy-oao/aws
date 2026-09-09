
import { useState } from "react";


function Ex3ToDoList() {

    const [datas, setDatas] = useState({todo:''});
    const [num, setNum] = useState([]);
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

    
    return(
        <div>
            <form onSubmit={submit}>
            <input type="text" name="todo" onChange={inputChange} value={datas.todo}/>
            <button>등록</button>
            </form>
            <h1>오늘의 할일</h1>
            <ul>
                {
                    todos.map(t=>{
                        return(
                            <li key={t.num}>{t.todo}</li>
                            
                        )
                    }
                    )
                }   

            </ul>
        </div>
    )
    
}

export default Ex3ToDoList;