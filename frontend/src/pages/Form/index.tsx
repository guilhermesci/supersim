import './styles.css';

function Form() {

    return (
        <div className="supersim-form-container">
            <div className="supersim-card-bottom-container">
                <h3>Novo Cadastro</h3>
                <form className="supersim-form">
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
                <button className="btn btn-primary supersim-btn mt-3">Cancelar</button>
            </div >
        </div >
    );
}

export default Form;
