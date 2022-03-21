import axios, { AxiosRequestConfig } from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { BASE_URL } from 'utils/requests';
import { validateEmail } from 'utils/validate';
import './styles.css';

function FormCardCreate() {

    const navigate = useNavigate();

    const handleSubmit = (event:React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        const cpf = (event.target as any).cpf.value;
        const name = (event.target as any).name.value;
        const email = (event.target as any).email.value;
        const income = (event.target as any).income.value;

        if (!validateEmail(email)){
            return;
        }

        const config: AxiosRequestConfig = {
            baseURL: BASE_URL,
            method: 'POST',
            url: `/clients`,
            data: {
                cpf: cpf,
                name: name,
                email: email,
                income: income
            }
        }

        axios(config).then(response => {
            navigate("/clients");
        });

    }

    return (
        <div className="supersim-form-container">
            <div className="supersim-card-bottom-container">
                <h3>Cadastrar Cliente</h3>
                <form className="supersim-form" onSubmit={handleSubmit}>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="cpf">Cpf</label>
                        <input type="cpf" className="form-control" id="cpf" />
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="name">Nome</label>
                        <input type="name" className="form-control" id="name" />
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="email">Email</label>
                        <input type="email" className="form-control" id="email" />
                    </div>
                    <div className="form-group supersim-form-group">
                        <label htmlFor="income">Renda mensal</label>
                        <input type="number" className="form-control" id="income" />
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

export default FormCardCreate;