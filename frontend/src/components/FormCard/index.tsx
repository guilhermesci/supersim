import axios, { AxiosRequestConfig } from 'axios';
import { useEffect } from 'react';
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Client } from 'types/client';
import { BASE_URL } from 'utils/requests';
import { validateEmail } from 'utils/validate';
import './styles.css';

type Props = {
    clientId: string;
}

function FormCard( { clientId } : Props) {

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

        //const id = client?.id;
        //const id = clientId;
        const cpf = (event.target as any).cpf.value;
        const name = (event.target as any).name.value;
        const email = (event.target as any).email.value;
        const income = (event.target as any).income.value;

        if (!validateEmail(email)){
            return;
        }

        const config: AxiosRequestConfig = {
            baseURL: BASE_URL,
            method: 'PUT',
            url: `/clients/${clientId}`,
            data: {
                id: clientId,
                cpf: cpf,
                name: name,
                email: email,
                income: income
            }
        }

        axios(config).then(response => {
            navigate("/");
        });

    }

    return (
        <div className="supersim-form-container">
            <div className="supersim-card-bottom-container">
                <h3>Editar Cadastro</h3>
                <form className="supersim-form" onSubmit={handleSubmit}>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="cpf">Cpf</label>
                        <input type="cpf" className="form-control" id="cpf" value={cpf} onChange={(e) => setCpf(e.target.value)}/>
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="name">Nome</label>
                        <input type="name" className="form-control" id="name" value={name} onChange={(e) => setName(e.target.value)}/>
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="email">Email</label>
                        <input type="email" className="form-control" id="email" value={email} onChange={(e) => setEmail(e.target.value)}/>
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="income">Renda mensal</label>
                        <input type="number" className="form-control" id="income" value={income} onChange={(e) => setIncome(e.target.value)}/>
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