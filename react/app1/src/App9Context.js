import { createContext, useContext, useState, useEffect } from "react";


const AuthContext = createContext(null);

function AuthProvider({children}) {
    const[user, setUser] = useState(null);

    //서버에 회원 정보를 요청해서 tser에 변경하는 함수
    const getMeAndSetUser = async () => {
        
        //비동기 통신으로 서버에 로그인한 회원 정보를 가져옴
        const res = {
            id : "abc123",
            nickname : "홍길동"
        }
        setUser(res);
    }

    useEffect(()=>{
        getMeAndSetUser();
    },[])

    return(
        <AuthContext.Provider value={{user, setUser}}>
            {children}
        </AuthContext.Provider>
    )
}

const useAuth = ()=>useContext(AuthContext);

export {AuthProvider, useAuth};