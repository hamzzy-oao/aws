import Home from "./Home";
import Login from "./Login";
import Signup from "./Signup";
import MyDiary from "./MyDiary";
import { DiaryCalendar, MyPage } from "./MyPage";
import DiaryDetail from "./DiaryDetail";
import DiaryEdit from "./DiaryEdit";


import { Route, Routes } from "react-router-dom";

function Main() {
    return(
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login/>}/>
            <Route path="/signup" element={<Signup/>}/>
            <Route path="/mydiary" element={<MyDiary />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/calendar" element={<DiaryCalendar />} />
            <Route path="/diary/:id" element={<DiaryDetail />} />
            <Route path="/diary/edit/:id" element={<DiaryEdit />} />

        </Routes>
    )
    

}

export default Main;