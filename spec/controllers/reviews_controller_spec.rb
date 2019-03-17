require 'rails_helper'

RSpec.describe ReviewsController, type: :controller do
  let!(:concept) { Concept.create! }
  let!(:question) { concept.questions.create! }

  describe 'GET #new' do
    it 'returns http success' do
      get :new
      expect(response).to have_http_status(:success)
    end
  end

  describe 'POST #create' do
    it 'returns http success' do
      post :create, review: { concept_ratings: { concept.id => 4 }.to_json }
      expect(response).to redirect_to(new_review_path)
    end
  end
end
