import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import List from 'pages/List';
import Form from 'pages/Form';
import Navbar from "components/Navbar";
import Home from "pages/Home";
import FormCreate from "pages/FormCreate";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/client" element={<FormCreate />} />
        <Route path="/clients" element={<List />} />
        <Route path="/clients" >
          <Route path=":clientId" element={<Form />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;