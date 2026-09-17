import { useAuth } from "./AuthContext";
import Statistics from "./Statistics";   // 추가
import "./CSS/Home.css";


function Home() {

    const { user, isLoggedIn } = useAuth();

    return(
        <main className="home-page">
            <section className="home-container">
                <div className="home-title-area">
                    <span className="home-icon">📔</span>
                    <p className="home-small-title">MY DAILY STORY</p>
                    <h1 className="home-title">오늘의 일기</h1>
                </div>

                {isLoggedIn && user && (
                    <div className="home-card">
                        <p className="home-date-message">오늘의 기록</p>

                        <h2 className="home-greeting">
                            <span>{user.nickname}</span>님 반갑습니다.
                        </h2>

                        <p className="home-description">
                            오늘 하루는 어떠셨나요?
                            <br />
                            지금의 감정과 이야기를
                            기록해보세요.
                        </p>

                        <Statistics />   {/* 추가 */}
                    </div>
                )}

                {!isLoggedIn && (
                    <div className="home-card">
                        <h2 className="home-greeting">나만의 하루를 기록해보세요.</h2>
                        <p className="home-description">
                            로그인하면 오늘의 감정과
                            이야기를 일기로 남길 수 있습니다.
                        </p>
                    </div>
                )}
            </section>
        </main>
    );
}

export default Home;