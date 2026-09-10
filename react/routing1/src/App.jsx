import {BrowserRouter} from "react-router-dom";
import { Header, Footer } from "./Layout";
import Main from "./Main";
import 'bootstrap/dist/css/bootstrap.min.css';




function App() {
  return (
    <BrowserRouter>
      <Header />
      <Main />
      <Footer />
    </BrowserRouter>
  );
}

export default App;
