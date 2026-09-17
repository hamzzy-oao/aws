import {BrowserRouter} from "react-router-dom";
import { Header } from "./Layout";
import Main from "./Main";
import { AuthProvider } from "./AuthContext";


function App() {
  return (
    <BrowserRouter>
    <AuthProvider>

       <Header />
       <Main />
     </AuthProvider>
    </BrowserRouter>
  );
}

export default App;