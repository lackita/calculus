require 'rails_helper'

RSpec.describe Review, type: :model do
  it 'returns nil when no previous reviews' do
    r = Review.new(concept: Concept.new)
    expect(r.previous_review).to be_nil
  end
end
