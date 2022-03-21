import axios, { AxiosRequestConfig } from 'axios';
import { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Client } from 'types/client';
import { BASE_URL } from 'utils/requests';
import './styles.css';

type Props = {
    clientId: string;
}

function FormCardDelete( { clientId } : Props) {

    const navigate = useNavigate();

    const [client, setClient] = useState<Client>();
    const [cpf, setCpf] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [income, setIncome] = useState("");

    useEffect(() => {
        axios.get(`${BASE_URL}/clients/${clientId}`)
        .then(response => {
            setClient(response.data)
            setCpf(response.data.cpf)
            setName(response.data.name)
            setEmail(response.data.email)
            setIncome(response.data.income)
        });
    }, [clientId]);

    const handleSubmit = (event:React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        const config: AxiosRequestConfig = {
            baseURL: BASE_URL,
            method: 'DELETE',
            url: `/clients/${clientId}`,
            data: {
                id: clientId
            }
        }

        axios(config).then(response => {
            navigate("/clients");
        });

    }

    return (
        <div className="supersim-form-container">
            <div className="supersim-card-bottom-container">
                <h3>Excluir Cliente</h3>
                <form className="supersim-form" onSubmit={handleSubmit}>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="cpf">Cpf</label>
                        <input type="cpf" className="form-control" id="cpf" value={cpf}/>
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="name">Nome</label>
                        <input type="name" className="form-control" id="name" value={name}/>
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="email">Email</label>
                        <input type="email" className="form-control" id="email" value={email}/>
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="income">Renda mensal</label>
                        <input type="number" className="form-control" id="income" value={income}/>
                    </div>
                    <div className="supersim-form-btn-container">
                        <button type="submit" className="btn btn-primary supersim-btn">Excluir</button>
                    </div>
                </form >
                <Link to='/clients'>
                    <button className="btn btn-primary supersim-btn mt-3">Cancelar</button>
                </Link>
                
            </div >
        </div >
    );
}

export default FormCardDelete;