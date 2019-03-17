class Concept < ActiveRecord::Base
  has_many :concepts_questions, dependent: :destroy
  has_many :questions, through: :concepts_questions
  has_many :reviews, dependent: :destroy

  before_create :set_initial_next_review

  def set_initial_next_review
    self.next_review ||= Time.current
  end

  def incorporate_review
    update(next_review: Time.current + repetition_interval.days)
  end

  def repetition_interval
    return 0 if reviews.last.rating < 4
    return 1 unless reviews.count > 1 && last_interval > 0
    return 6 if last_interval == 1

    last_interval * reviews.last.easiness_factor
  end

  def last_interval
    ((reviews[-1].created_at - reviews[-2].created_at) / 1.day).round
  end

  def appropriate_question
    questions.order('random()').first
  end
end
