defmodule CodebrothersCustomerWeb.Router do
  use CodebrothersCustomerWeb, :router

  pipeline :api do
    plug :accepts, ["json"]
  end

  scope "/api", CodebrothersCustomerWeb do
    pipe_through :api
  end

  pipeline :browser do
    plug(:accepts, ["html"])
  end

  scope "/", CodebrothersCustomerWeb do
    pipe_through :browser
    resources "/customer", CustomerController, except: [:new, :edit]
    get "/", DefaultController, :index
  end


end
