defmodule CodebrothersCustomer.Repo.Migrations.CreateTbCustomer do
  use Ecto.Migration

  def change do
    create table(:tb_customer) do
      add :id, :integer
      add :ativo, :boolean, default: false, null: false
      add :atualizadoem, :naive_datetime
      add :cpf, :string
      add :criadoem, :naive_datetime
      add :datanascimento, :naive_datetime
      add :email, :string
      add :nome, :string
      add :rg, :string
      add :sobrenome, :string

      timestamps()
    end

  end
end
