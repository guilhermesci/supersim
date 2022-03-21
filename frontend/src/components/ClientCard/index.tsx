import { Link } from "react-router-dom";
import { Client } from "types/client";

type Props = {
    client: Client;
}

function ClientCard( { client } : Props) {

    return (
        <div>
            <div className="supersim-card-bottom-container">
                <h3>{client.name}</h3>
                <Link to={`/clients/${client.id}`}>
                    <div className="btn btn-primary supersim-btn">Editar</div>
                </Link>
                <br/>
                <Link to={`/client/delete/${client.id}`}>
                    <div className="btn btn-primary supersim-btn">Excluir</div>
                </Link>
            </div>
        </div>
    );
}

export default ClientCard;