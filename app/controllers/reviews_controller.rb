class ReviewsController < ApplicationController
  def new
    c = Concept.order(:next_review).first
    @review = Review.new(question: c.appropriate_question)
    raise(StandardError, "No appropriate questions for #{c.id}: #{c.name}") if c.questions.blank?
  end

  def create
    params[:review][:concept_ratings] = JSON.parse(params[:review][:concept_ratings])
    if params[:review][:correct] == 'false'
      params[:review][:concept_ratings].map! do |_, r|
        r == 3 ? 0 : r
      end
    end

    Review.create!(params[:review].permit(:concept_ratings, :question_id))
    redirect_to new_review_path
  end
end
