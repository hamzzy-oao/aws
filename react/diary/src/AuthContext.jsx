import { createContext, useContext, useEffect, useState } from "react";


const AuthContext = createContext(null);

function AuthProvider({ children }) {

    const [accessToken, setAccessToken] = useState (() => {
        return localStorage.getItem("accessToken");
    });

    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {

        const getMeAndSetUser = async () => {

            
            if(!accessToken){
                setUser(null);
                setLoading(false);
                return;
            }
            try{
                const response = await fetch("/api/auth/me",{
                        method: "GET",
                        headers: { Authorization : `Bearer ${accessToken}`
                        }
                });

                //만약 토큰이 만료 또는 잘못될 경우
                if(response.status === 401 || response.status === 403){
                    localStorage.removeItem("accessToken");
                    // React의 토큰 state도 제거
                    setAccessToken(null);
                    // 로그인 회원 정보도 제거 후 종료
                    setUser(null);
                    return;
                }

                const result = await response.json();
                setUser(result);
                
            }catch(e){
                console.error(e);
                setUser(null);
                
            }finally {
                setLoading(false);
            }
        };
        getMeAndSetUser();
    },[accessToken]);
    
    const logout = () => {
        localStorage.removeItem("accessToken");
        //로그아웃 시 리액트 토큰 변경
        setAccessToken(null);
        //로그아웃 시 회원정보 변경
        setUser(null);

    } 

    const login = (token) => {

        localStorage.setItem("accessToken",token);
        setAccessToken(token);
        
    }
    
    const isLoggedIn = user !== null;

    return (
        <AuthContext.Provider
            value={{
                // DB에서 조회한 로그인 회원 정보
                user,
                // JWT 액세스 토큰
                accessToken,
                // 로그인 여부
                isLoggedIn,
                // 회원 정보 조회 중인지 여부
                loading,
                // 로그인 처리 함수 
                login,
                // 로그아웃 처리 함수 
                logout 
            }}
        >{children}
        </AuthContext.Provider>
    )

    
    
}

function useAuth() {
  const context = useContext(AuthContext);

  if (context === null) {
      throw new Error(
          "useAuth는 AuthProvider 안에서 사용해야 합니다."
      );
  }

  return context;
}
// 다른 파일에서 사용할 수 있도록 내보냄
export { AuthProvider, useAuth };