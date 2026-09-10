import { use, useMemo, useState } from "react";

function App8UseMemo() {

    const [num, setNum] =useState(0);
    const [page, setPage] =useState(0);

    const calc = useMemo(() =>{
        console.log("page를 이용해서 계산 중...\n시간이 오래 걸리는 계산 중...(20초 걸림)")
        return 1;
    },[page])

    const arr1 = {
        name : "홍길동",
        age : 21
    };

    let name = "홍길동";
    const arr2 = useMemo(() => {
        return {
            name: name,
            age: 21
        };
    }, [name]);
    
    

    console.log(calc);
    
    return(
        <div>
            <button onClick={()=>setNum(num+1)}>렌더링 버튼</button>
            <button onClick={()=>setPage(page+1)}>페이지 변경 버튼</button>
        </div>
    )
    
}

export default App8UseMemo;