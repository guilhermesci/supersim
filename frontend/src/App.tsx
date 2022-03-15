import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import Loans from 'pages/Loans';
import Loan from 'pages/Loan';
import Navbar from "components/Navbar";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Loans />} />
        <Route path="/loan">
          <Route path=":loanId" element={<Loan />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
