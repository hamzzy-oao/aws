import Home from "./Home";
import Login from "./Login";
import Signup from "./Signup";
import MyDiary from "./MyDiary";

import { Route, Routes } from "react-router-dom";

function Main() {
    return(
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login/>}/>
            <Route path="/signup" element={<Signup/>}/>
            <Route path="/mydiary" element={<MyDiary />} />

        </Routes>
    )
    

}

export default Main;