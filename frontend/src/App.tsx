import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import List from 'pages/List';
import Form from 'pages/Form';
import Navbar from "components/Navbar";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<List />} />
        <Route path="/clients" >
          <Route path=":clientCpf" element={<Form />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;