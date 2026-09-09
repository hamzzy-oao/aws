import {BrowserRouter} from "react-router-dom";
import { Header, Footer } from "./Layout";
import Main from "./Main";



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
