class QuestionsController < ApplicationController
  def show
    id = params[:id]
    @question = Question.find(id)
  end
end
