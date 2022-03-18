import { Link } from "react-router-dom";

function ClientCard() {

    const client = {name:"Nome Cliente"}

    return (
        <div>
            <div className="supersim-card-bottom-container">
                <h3>{client.name}</h3>
                <Link to='/client'>
                    <div className="btn btn-primary supersim-btn">Exibir Empréstimos</div>
                </Link>
            </div>
        </div>
    );
}

export default ClientCard;
