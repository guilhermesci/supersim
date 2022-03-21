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
import FormDelete from "pages/FormDelete";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/client" element={<FormCreate />} />
        <Route path="/client/delete" >
          <Route path=":clientId" element={<FormDelete />} />
        </Route>
        <Route path="/clients" element={<List />} />
        <Route path="/clients" >
          <Route path=":clientId" element={<Form />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;