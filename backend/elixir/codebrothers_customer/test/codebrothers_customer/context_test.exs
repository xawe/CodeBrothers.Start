defmodule CodebrothersCustomer.ContextTest do
  use CodebrothersCustomer.DataCase

  alias CodebrothersCustomer.Context

  describe "tb_customer" do
    alias CodebrothersCustomer.Context.Customer

    @valid_attrs %{ativo: true, atualizadoem: ~N[2010-04-17 14:00:00], cpf: "some cpf", criadoem: ~N[2010-04-17 14:00:00], datanascimento: ~N[2010-04-17 14:00:00], email: "some email", id: 42, nome: "some nome", rg: "some rg", sobrenome: "some sobrenome"}
    @update_attrs %{ativo: false, atualizadoem: ~N[2011-05-18 15:01:01], cpf: "some updated cpf", criadoem: ~N[2011-05-18 15:01:01], datanascimento: ~N[2011-05-18 15:01:01], email: "some updated email", id: 43, nome: "some updated nome", rg: "some updated rg", sobrenome: "some updated sobrenome"}
    @invalid_attrs %{ativo: nil, atualizadoem: nil, cpf: nil, criadoem: nil, datanascimento: nil, email: nil, id: nil, nome: nil, rg: nil, sobrenome: nil}

    def customer_fixture(attrs \\ %{}) do
      {:ok, customer} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Context.create_customer()

      customer
    end

    test "list_tb_customer/0 returns all tb_customer" do
      customer = customer_fixture()
      assert Context.list_tb_customer() == [customer]
    end

    test "get_customer!/1 returns the customer with given id" do
      customer = customer_fixture()
      assert Context.get_customer!(customer.id) == customer
    end

    test "create_customer/1 with valid data creates a customer" do
      assert {:ok, %Customer{} = customer} = Context.create_customer(@valid_attrs)
      assert customer.ativo == true
      assert customer.atualizadoem == ~N[2010-04-17 14:00:00]
      assert customer.cpf == "some cpf"
      assert customer.criadoem == ~N[2010-04-17 14:00:00]
      assert customer.datanascimento == ~N[2010-04-17 14:00:00]
      assert customer.email == "some email"
      assert customer.id == 42
      assert customer.nome == "some nome"
      assert customer.rg == "some rg"
      assert customer.sobrenome == "some sobrenome"
    end

    test "create_customer/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Context.create_customer(@invalid_attrs)
    end

    test "update_customer/2 with valid data updates the customer" do
      customer = customer_fixture()
      assert {:ok, %Customer{} = customer} = Context.update_customer(customer, @update_attrs)
      assert customer.ativo == false
      assert customer.atualizadoem == ~N[2011-05-18 15:01:01]
      assert customer.cpf == "some updated cpf"
      assert customer.criadoem == ~N[2011-05-18 15:01:01]
      assert customer.datanascimento == ~N[2011-05-18 15:01:01]
      assert customer.email == "some updated email"
      assert customer.id == 43
      assert customer.nome == "some updated nome"
      assert customer.rg == "some updated rg"
      assert customer.sobrenome == "some updated sobrenome"
    end

    test "update_customer/2 with invalid data returns error changeset" do
      customer = customer_fixture()
      assert {:error, %Ecto.Changeset{}} = Context.update_customer(customer, @invalid_attrs)
      assert customer == Context.get_customer!(customer.id)
    end

    test "delete_customer/1 deletes the customer" do
      customer = customer_fixture()
      assert {:ok, %Customer{}} = Context.delete_customer(customer)
      assert_raise Ecto.NoResultsError, fn -> Context.get_customer!(customer.id) end
    end

    test "change_customer/1 returns a customer changeset" do
      customer = customer_fixture()
      assert %Ecto.Changeset{} = Context.change_customer(customer)
    end
  end
end
