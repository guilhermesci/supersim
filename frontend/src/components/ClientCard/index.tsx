function ClientCard() {

    const client = {name:"Nome Cliente"}

    return (
        <div>
            <div className="supersim-card-bottom-container">
                <h3>{client.name}</h3>
                <div className="btn btn-primary supersim-btn">Exibir Empréstimos</div>
            </div>
        </div>
    );
}

export default ClientCard;
