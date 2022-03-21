import { Link } from "react-router-dom";

function Home() {

    return (
        <>
            <div>
                <div className="supersim-card-bottom-container">
                    <Link to='/client'>
                        <button className="btn btn-primary supersim-btn mt-3">Cadastrar Cliente</button>
                    </Link>
                    <Link to='/clients'>
                        <button className="btn btn-primary supersim-btn mt-3">Lista de Clientes</button>
                    </Link>
                </div>
            </div>
        </>
    );
}

export default Home;