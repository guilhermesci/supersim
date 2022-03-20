import axios from 'axios';
import { useEffect } from 'react';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import { Client } from 'types/client';
import { BASE_URL } from 'utils/requests';
import './styles.css';

type Props = {
    clientCpf: string;
}

function FormCard( { clientCpf } : Props) {

    const [client, setClient] = useState<Client>();

    useEffect(() => {
        axios.get(`${BASE_URL}/clients/${clientCpf}`)
        .then(response => {
            setClient(response.data)
        });
    }, [clientCpf]);

    return (
        <div className="supersim-form-container">
            <div className="supersim-card-bottom-container">
                <h3>Editar Cadastro</h3>
                <form className="supersim-form">
                    <div className="form-group supersim-form-group">
                        <label htmlFor="cpf">Cpf</label>
                        <input type="cpf" className="form-control" id="cpf" value={client?.cpf} />
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="name">Nome</label>
                        <input type="name" className="form-control" id="name" value={client?.name} />
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="email">Email</label>
                        <input type="email" className="form-control" id="email" value={client?.email} />
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="income">Renda mensal</label>
                        <input type="number" className="form-control" id="income" value={client?.income} />
                    </div>
                    <div className="supersim-form-btn-container">
                        <button type="submit" className="btn btn-primary supersim-btn">Salvar</button>
                    </div>
                </form >
                <Link to='/'>
                    <button className="btn btn-primary supersim-btn mt-3">Cancelar</button>
                </Link>
                
            </div >
        </div >
    );
}

export default FormCard;