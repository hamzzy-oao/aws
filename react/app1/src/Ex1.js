import { useState } from "react";


function Ex1() {

    const [datas, setDatas] = useState({text:''});

    const submit = e => {
        e.preventDefault();
        if(!datas.text || datas.text.length === 0){
            alert("내용을 입력하세요.");
            return;
        }
        
        alert(datas.text);
        //입력한 내용 지우기
        setDatas({...datas, text: ''})
        
    }

    const inputChange = e => {
        const{name, value} = e.target;
        setDatas({...datas,[name]: value})
    }
    


    return(
        <form onSubmit={submit}>
            <input type="text" name="text" onChange={inputChange}/>
            <button>확인</button>
        </form>
    )
    
}

export default Ex1;